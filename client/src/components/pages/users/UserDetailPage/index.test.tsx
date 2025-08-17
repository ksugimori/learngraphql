import { render, screen } from "@testing-library/react";
import {
  useQueryLoader,
  usePreloadedQuery,
  useMutation,
  useRelayEnvironment,
} from "react-relay";
import { useParams } from "react-router-dom";
import { beforeEach, describe, expect, test, vi } from "vitest";

import { UserDetailPage } from "./index";

type MockRelayEnvironment = {
  getStore: () => {
    invalidateStore: () => void;
  };
};

type MockQueryRef = {
  kind: string;
};

vi.mock("react-relay", () => ({
  useQueryLoader: vi.fn(),
  usePreloadedQuery: vi.fn(),
  useMutation: vi.fn(),
  useRelayEnvironment: vi.fn(),
  useFragment: vi.fn((_, data) => data),
  graphql: () => ({}),
}));

vi.mock("react-router-dom", () => ({
  useParams: vi.fn(),
}));

const mockUseQueryLoader = vi.mocked(useQueryLoader);
const mockUsePreloadedQuery = vi.mocked(usePreloadedQuery);
const mockUseMutation = vi.mocked(useMutation);
const mockUseRelayEnvironment = vi.mocked(useRelayEnvironment);
const mockUseParams = vi.mocked(useParams);

describe("UserDetailPage", () => {
  beforeEach(() => {
    vi.clearAllMocks();

    // useRelayEnvironment のモック
    const mockEnvironment: MockRelayEnvironment = {
      getStore: () => ({
        invalidateStore: vi.fn(),
      }),
    };
    mockUseRelayEnvironment.mockReturnValue(mockEnvironment as never);

    // useMutation のモック
    mockUseMutation.mockReturnValue([vi.fn(), false]);
  });

  describe("ローディング状態", () => {
    test("queryRef が null の場合は Loading が表示される", () => {
      // Given
      mockUseParams.mockReturnValue({ userId: "1" });

      // queryRef が null の状態
      mockUseQueryLoader.mockReturnValue([null, vi.fn(), vi.fn()]);

      // When
      render(<UserDetailPage />);

      // Then
      expect(screen.getByText("Loading...")).toBeInTheDocument();
    });
  });

  describe("正常系", () => {
    test("レスポンスのユーザー名タイトルに表示される", () => {
      // Given
      mockUseParams.mockReturnValue({ userId: "1" });

      // useQueryLoader のモック（queryRef が存在する状態）
      const mockQueryRef: MockQueryRef = { kind: "PreloadedQuery" };
      mockUseQueryLoader.mockReturnValue([
        mockQueryRef as never,
        vi.fn(),
        vi.fn(),
      ]);

      // usePreloadedQuery のモック
      mockUsePreloadedQuery.mockReturnValue({
        user: {
          name: "Alice",
          todos: [],
        },
      });

      // When
      render(<UserDetailPage />);

      // Then
      expect(screen.getByText("User: Alice")).toBeInTheDocument();
    });

    test("ユーザーに紐づく Todo のタイトルが表示される", () => {
      // Given
      mockUseParams.mockReturnValue({ userId: "1" });

      const mockQueryRef: MockQueryRef = { kind: "PreloadedQuery" };
      mockUseQueryLoader.mockReturnValue([
        mockQueryRef as never,
        vi.fn(),
        vi.fn(),
      ]);

      mockUsePreloadedQuery.mockReturnValue({
        user: {
          name: "Alice",
          todos: [
            { id: "1", title: "牛乳を買う", isCompleted: false },
            { id: "2", title: "掃除", isCompleted: true },
          ],
        },
      });

      // When
      render(<UserDetailPage />);

      // Then
      expect(screen.getByText("牛乳を買う")).toBeInTheDocument();
      expect(screen.getByText("掃除")).toBeInTheDocument();
    });
  });

  describe("異常系", () => {
    test("レスポンスが null ならエラーメッセージが表示される", () => {
      // Given
      mockUseParams.mockReturnValue({ userId: "999" });

      const mockQueryRef: MockQueryRef = { kind: "PreloadedQuery" };
      mockUseQueryLoader.mockReturnValue([
        mockQueryRef as never,
        vi.fn(),
        vi.fn(),
      ]);

      mockUsePreloadedQuery.mockReturnValue({ user: null });

      // When
      render(<UserDetailPage />);

      // Then
      expect(screen.getByText("User Not Found.")).toBeInTheDocument();
    });
  });
});
