// src/components/Layout/index.tsx
import { Outlet } from "react-router-dom";

import { Header } from "./Header";
import styles from "./style.module.css";

export const Layout = () => {
  return (
    <div className={styles.root}>
      <Header />
      <main>
        <Outlet /> {/* ここに各ページのコンテンツが表示される */}
      </main>
    </div>
  );
};
