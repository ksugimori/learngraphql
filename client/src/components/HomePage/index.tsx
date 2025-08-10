import type React from "react";
import { Link } from "react-router-dom";

import styles from "./style.module.css";

export const HomePage: React.FC = () => {
  return (
    <main>
      <h1>Simple TODO</h1>

      <nav className={styles.navigation}>
        <ul>
          <li>
            <Link to="/users">Users</Link>
          </li>
          <li>
            <Link to="/users/new">Create User</Link>
          </li>
        </ul>
      </nav>
    </main>
  );
};
