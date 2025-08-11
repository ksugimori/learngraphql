import { Link } from "react-router-dom";

import styles from "./style.module.css";

import type React from "react";

export const HomePage: React.FC = () => {
  return (
    <section>
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
    </section>
  );
};
