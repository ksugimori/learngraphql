import { render, screen } from "@testing-library/react";
import { useLazyLoadQuery } from "react-relay";
import { MemoryRouter } from "react-router-dom";
import { beforeEach, describe, expect, test, vi } from "vitest";

import { UsersPage } from "./index";

vi.mock("react-relay", () => ({
  useLazyLoadQuery: vi.fn(),
  useFragment: vi.fn((_, data) => data),
  graphql: () => ({}),
}));

const mockUseLazyLoadQuery = vi.mocked(useLazyLoadQuery);

describe("UsersPage", () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  test("レスポンスで返されたユーザー名のリストが表示される", () => {
    // Given
    mockUseLazyLoadQuery.mockReturnValue({
      users: [
        { id: "1", name: "Alice" },
        { id: "2", name: "Bob" },
      ],
    });

    // When
    render(
      <MemoryRouter>
        <UsersPage />
      </MemoryRouter>
    );

    // Then
    expect(screen.getByText("Users")).toBeInTheDocument();
    expect(screen.getByText("Alice")).toBeInTheDocument();
    expect(screen.getByText("Bob")).toBeInTheDocument();
  });
});
