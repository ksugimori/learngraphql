import styles from "./style.module.css";

import type { ReactNode } from "react";
import type React from "react";

type Props = {
  left: ReactNode;
  right: ReactNode;
};

export const ListItem: React.FC<Props> = ({ left, right }) => {
  return (
    <div className={styles.root}>
      <div className={styles.left}>{left}</div>
      <div className={styles.separator}></div>
      <div className={styles.right}>{right}</div>
    </div>
  );
};
