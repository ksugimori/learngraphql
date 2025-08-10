import { render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";

import { UsersPage } from "./index";

// Mock react-relay for testing
vi.mock("react-relay", () => ({
  useLazyLoadQuery: () => ({
    users: [
      { id: "1", name: "Alice" },
      { id: "2", name: "Bob" },
    ],
  }),
  graphql: () => ({}),
}));

describe("UsersPage", () => {
  it("renders users list", () => {
    render(<UsersPage />);

    expect(screen.getByText("Users")).toBeInTheDocument();
    expect(screen.getByText("Alice")).toBeInTheDocument();
    expect(screen.getByText("Bob")).toBeInTheDocument();
  });
});
