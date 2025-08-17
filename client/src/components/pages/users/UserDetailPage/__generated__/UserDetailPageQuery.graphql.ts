/**
 * @generated SignedSource<<98c58ace312b68003223a98b9e836cd1>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
import { FragmentRefs } from "relay-runtime";
export type UserDetailPageQuery$variables = {
  userId: string;
};
export type UserDetailPageQuery$data = {
  readonly user: {
    readonly name: string;
    readonly " $fragmentSpreads": FragmentRefs<"TodoListFragment">;
  } | null | undefined;
};
export type UserDetailPageQuery = {
  response: UserDetailPageQuery$data;
  variables: UserDetailPageQuery$variables;
};

const node: ConcreteRequest = (function(){
var v0 = [
  {
    "defaultValue": null,
    "kind": "LocalArgument",
    "name": "userId"
  }
],
v1 = [
  {
    "kind": "Variable",
    "name": "id",
    "variableName": "userId"
  }
],
v2 = {
  "alias": null,
  "args": null,
  "kind": "ScalarField",
  "name": "name",
  "storageKey": null
},
v3 = {
  "alias": null,
  "args": null,
  "kind": "ScalarField",
  "name": "id",
  "storageKey": null
};
return {
  "fragment": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Fragment",
    "metadata": null,
    "name": "UserDetailPageQuery",
    "selections": [
      {
        "alias": null,
        "args": (v1/*: any*/),
        "concreteType": "User",
        "kind": "LinkedField",
        "name": "user",
        "plural": false,
        "selections": [
          (v2/*: any*/),
          {
            "args": null,
            "kind": "FragmentSpread",
            "name": "TodoListFragment"
          }
        ],
        "storageKey": null
      }
    ],
    "type": "Query",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "UserDetailPageQuery",
    "selections": [
      {
        "alias": null,
        "args": (v1/*: any*/),
        "concreteType": "User",
        "kind": "LinkedField",
        "name": "user",
        "plural": false,
        "selections": [
          (v2/*: any*/),
          {
            "alias": null,
            "args": null,
            "concreteType": "Todo",
            "kind": "LinkedField",
            "name": "todos",
            "plural": true,
            "selections": [
              (v3/*: any*/),
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
            "storageKey": null
          },
          (v3/*: any*/)
        ],
        "storageKey": null
      }
    ]
  },
  "params": {
    "cacheID": "604cbe1eb816bd1695c987736a83496f",
    "id": null,
    "metadata": {},
    "name": "UserDetailPageQuery",
    "operationKind": "query",
    "text": "query UserDetailPageQuery(\n  $userId: ID!\n) {\n  user(id: $userId) {\n    name\n    ...TodoListFragment\n    id\n  }\n}\n\nfragment TodoCard_todo on Todo {\n  title\n  isCompleted\n}\n\nfragment TodoListFragment on User {\n  todos {\n    id\n    ...TodoCard_todo\n  }\n}\n"
  }
};
})();

(node as any).hash = "9320dc0d5b181534f757dc1b40dd5cd8";

export default node;
