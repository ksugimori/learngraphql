import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import { TodoList } from "./components/TodoList";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/users/:userId/todos" element={<TodoList />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
