/**
 * @generated SignedSource<<2300d957a689aaeb397d2b98a8b5357a>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ReaderFragment } from 'relay-runtime';
import { FragmentRefs } from "relay-runtime";
export type TodoListFragment$data = {
  readonly todos: ReadonlyArray<{
    readonly id: string;
    readonly " $fragmentSpreads": FragmentRefs<"TodoListItem_todo">;
  }>;
  readonly " $fragmentType": "TodoListFragment";
};
export type TodoListFragment$key = {
  readonly " $data"?: TodoListFragment$data;
  readonly " $fragmentSpreads": FragmentRefs<"TodoListFragment">;
};

const node: ReaderFragment = {
  "argumentDefinitions": [],
  "kind": "Fragment",
  "metadata": null,
  "name": "TodoListFragment",
  "selections": [
    {
      "alias": null,
      "args": null,
      "concreteType": "Todo",
      "kind": "LinkedField",
      "name": "todos",
      "plural": true,
      "selections": [
        {
          "alias": null,
          "args": null,
          "kind": "ScalarField",
          "name": "id",
          "storageKey": null
        },
        {
          "args": null,
          "kind": "FragmentSpread",
          "name": "TodoListItem_todo"
        }
      ],
      "storageKey": null
    }
  ],
  "type": "User",
  "abstractKey": null
};

(node as any).hash = "34fa5b6f3140c66fe280f53d7252ec93";

export default node;
