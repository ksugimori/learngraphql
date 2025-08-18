import { render, screen } from "@testing-library/react";
import { useMutation } from "react-relay";
import { beforeEach, describe, expect, test, vi } from "vitest";

import { CreateUserPage } from "./index";

vi.mock("react-relay", () => ({
  useMutation: vi.fn(),
  graphql: () => ({}),
}));

vi.mock("react-router-dom", () => ({
  useNavigate: vi.fn(),
}));

const mockUseMutation = vi.mocked(useMutation);

describe("CreateUserPage", () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  test("初期表示でフォームは空の状態になる", () => {
    // Given
    mockUseMutation.mockReturnValue([vi.fn(), false]);

    // When
    render(<CreateUserPage />);

    // Then
    expect(screen.getByDisplayValue("")).toBeInTheDocument();
  });

  test("Submit ボタンをクリックすると mutation が呼ばれる", () => {
    // Given
    const mockMutate = vi.fn();
    mockUseMutation.mockReturnValue([mockMutate, false]);

    // When
    render(<CreateUserPage />);

    const input = screen.getByLabelText<HTMLInputElement>("name-input");
    const submitButton = screen.getByRole("button", {
      name: /submit/i,
    });
    input.value = "new user";
    submitButton.click();

    // Then
    expect(mockMutate).toHaveBeenCalled();
  });
});
