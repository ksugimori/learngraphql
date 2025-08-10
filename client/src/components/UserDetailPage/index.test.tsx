import { render, screen } from "@testing-library/react";
import { useLazyLoadQuery } from "react-relay";
import { useParams } from "react-router-dom";
import { beforeEach, describe, expect, it, vi } from "vitest";

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
    it("レスポンスのユーザー名タイトルに表示される", () => {
      mockUseParams.mockReturnValue({ userId: "1" });
      mockUseLazyLoadQuery.mockReturnValue({
        user: {
          name: "Alice",
          todos: [],
        },
      });

      render(<UserDetailPage />);
      expect(screen.getByText("User: Alice")).toBeInTheDocument();
    });

    it("ユーザーに紐づく Todo のタイトルが表示される", () => {
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

      render(<UserDetailPage />);
      expect(screen.getByText("牛乳を買う")).toBeInTheDocument();
      expect(screen.getByText("掃除")).toBeInTheDocument();
    });
  });

  describe("異常系", () => {
    it("レスポンスが null ならエラーメッセージが表示される", () => {
      mockUseParams.mockReturnValue({ userId: "999" });
      mockUseLazyLoadQuery.mockReturnValue({ user: null });

      render(<UserDetailPage />);
      expect(screen.getByText("User Not Found. ID: 999")).toBeInTheDocument();
    });
  });
});
