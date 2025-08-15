import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";

import { Layout } from "./components/Layout";
import { HomePage } from "./components/pages/HomePage";
import { CreateUserPage } from "./components/pages/users/CreateUserPage";
import { UserDetailPage } from "./components/pages/users/UserDetailPage";
import { UsersPage } from "./components/pages/users/UsersPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<HomePage />} />

          <Route path="users">
            <Route index element={<UsersPage />} />

            <Route path="new" element={<CreateUserPage />} />
            <Route path=":userId" element={<UserDetailPage />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
