import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";
import { CreateUserPage } from "./components/CreateUserPage";
import { HomePage } from "./components/HomePage";
import { UserDetailPage } from "./components/UserDetailPage";
import { UsersPage } from "./components/UsersPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<HomePage />} />

        <Route path="users">
          <Route index element={<UsersPage />} />

          <Route path="new" element={<CreateUserPage />} />
          <Route path=":userId" element={<UserDetailPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
