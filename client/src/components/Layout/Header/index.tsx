import { Link, NavLink } from "react-router-dom";

import styles from "./style.module.css";

import type React from "react";

export const Header: React.FC = () => {
  return (
    <header className={styles.root}>
      <Link to="/" className={styles.topLink}>
        <h1>Simple TODO</h1>
      </Link>
      <nav className={styles.navigation}>
        <ul>
          <li>
            <NavLink to="/users/new">Create User</NavLink>
          </li>
          <li>
            <NavLink to="/users">List Users</NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
};
