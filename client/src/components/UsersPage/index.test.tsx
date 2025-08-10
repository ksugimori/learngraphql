import { render, screen } from "@testing-library/react";
import { useLazyLoadQuery } from "react-relay";
import { describe, expect, it, vi } from "vitest";

import { UsersPage } from "./index";

vi.mock("react-relay", () => ({
  useLazyLoadQuery: vi.fn(),
  graphql: () => ({}),
}));

const mockUseLazyLoadQuery = vi.mocked(useLazyLoadQuery);

describe("UsersPage", () => {
  it("renders users list", () => {
    // Given
    mockUseLazyLoadQuery.mockReturnValue({
      users: [
        { id: "1", name: "Alice" },
        { id: "2", name: "Bob" },
      ],
    });

    // When
    render(<UsersPage />);

    // Then
    expect(screen.getByText("Users")).toBeInTheDocument();
    expect(screen.getByText("Alice")).toBeInTheDocument();
    expect(screen.getByText("Bob")).toBeInTheDocument();
  });
});
