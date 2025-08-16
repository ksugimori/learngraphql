import styles from "./style.module.css";

import type React from "react";
import type { ReactNode } from "react";

type Props = {
  children: ReactNode;
};

export const Card: React.FC<Props> = ({ children }) => {
  return <div className={styles.root}>{children}</div>;
};
