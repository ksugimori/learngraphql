import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import { UserDetailPage } from "./components/UserDetailPage";
import { UsersPage } from "./components/UsersPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="users">
          <Route index element={<UsersPage />} />
          <Route path=":userId" element={<UserDetailPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
