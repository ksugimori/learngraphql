import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import { UsersPage } from "./components/UsersPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/users/" element={<UsersPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
