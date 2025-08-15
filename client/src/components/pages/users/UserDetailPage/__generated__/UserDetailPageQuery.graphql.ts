/**
 * @generated SignedSource<<8593fbec6fa17b3266cba2b83cf4333e>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
export type UserDetailPageQuery$variables = {
  userId: string;
};
export type UserDetailPageQuery$data = {
  readonly user: {
    readonly name: string;
    readonly todos: ReadonlyArray<{
      readonly id: string;
      readonly isCompleted: boolean | null | undefined;
      readonly title: string;
    }>;
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
},
v4 = {
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
          (v4/*: any*/)
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
          (v4/*: any*/),
          (v3/*: any*/)
        ],
        "storageKey": null
      }
    ]
  },
  "params": {
    "cacheID": "99a25bd81dfe8e95c2aa7ebdae9dfbbe",
    "id": null,
    "metadata": {},
    "name": "UserDetailPageQuery",
    "operationKind": "query",
    "text": "query UserDetailPageQuery(\n  $userId: ID!\n) {\n  user(id: $userId) {\n    name\n    todos {\n      id\n      title\n      isCompleted\n    }\n    id\n  }\n}\n"
  }
};
})();

(node as any).hash = "48b881b179c42c9e03df5885eb021584";

export default node;
