/**
 * @generated SignedSource<<b86684db38d78457f3a372a97e971310>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ReaderFragment } from 'relay-runtime';
import { FragmentRefs } from "relay-runtime";
export type TodoCard_todo$data = {
  readonly isCompleted: boolean;
  readonly title: string;
  readonly " $fragmentType": "TodoCard_todo";
};
export type TodoCard_todo$key = {
  readonly " $data"?: TodoCard_todo$data;
  readonly " $fragmentSpreads": FragmentRefs<"TodoCard_todo">;
};

const node: ReaderFragment = {
  "argumentDefinitions": [],
  "kind": "Fragment",
  "metadata": null,
  "name": "TodoCard_todo",
  "selections": [
    {
      "alias": null,
      "args": null,
      "kind": "ScalarField",
      "name": "title",
      "storageKey": null
    },
    {
      "alias": null,
      "args": null,
      "kind": "ScalarField",
      "name": "isCompleted",
      "storageKey": null
    }
  ],
  "type": "Todo",
  "abstractKey": null
};

(node as any).hash = "14988aa41f63ec6136941bea5062ae4e";

export default node;
