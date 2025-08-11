import { render, screen } from "@testing-library/react";
import { useLazyLoadQuery } from "react-relay";
import { useParams } from "react-router-dom";
import { beforeEach, describe, expect, test, vi } from "vitest";

import { UserDetailPage } from "./index";

vi.mock("react-relay", () => ({
  useLazyLoadQuery: vi.fn(),
  graphql: () => ({}),
}));

vi.mock("react-router-dom", () => ({
  useParams: vi.fn(),
}));

const mockUseLazyLoadQuery = vi.mocked(useLazyLoadQuery);
const mockUseParams = vi.mocked(useParams);

describe("UserDetailPage", () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  describe("正常系", () => {
    test("レスポンスのユーザー名タイトルに表示される", () => {
      // Given
      mockUseParams.mockReturnValue({ userId: "1" });
      mockUseLazyLoadQuery.mockReturnValue({
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
      mockUseLazyLoadQuery.mockReturnValue({
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
      mockUseLazyLoadQuery.mockReturnValue({ user: null });

      // When
      render(<UserDetailPage />);

      // Then
      expect(screen.getByText("User Not Found. ID: 999")).toBeInTheDocument();
    });
  });
});
