/**
 * @generated SignedSource<<da058442f43c94393402d8083065e4d0>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
export type UsersPageQuery$variables = Record<PropertyKey, never>;
export type UsersPageQuery$data = {
  readonly users: ReadonlyArray<{
    readonly id: string;
    readonly name: string;
  }>;
};
export type UsersPageQuery = {
  response: UsersPageQuery$data;
  variables: UsersPageQuery$variables;
};

const node: ConcreteRequest = (function(){
var v0 = [
  {
    "alias": null,
    "args": null,
    "concreteType": "User",
    "kind": "LinkedField",
    "name": "users",
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
        "alias": null,
        "args": null,
        "kind": "ScalarField",
        "name": "name",
        "storageKey": null
      }
    ],
    "storageKey": null
  }
];
return {
  "fragment": {
    "argumentDefinitions": [],
    "kind": "Fragment",
    "metadata": null,
    "name": "UsersPageQuery",
    "selections": (v0/*: any*/),
    "type": "Query",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": [],
    "kind": "Operation",
    "name": "UsersPageQuery",
    "selections": (v0/*: any*/)
  },
  "params": {
    "cacheID": "00f7e6b29e79320b9e9b75dc8a0c2862",
    "id": null,
    "metadata": {},
    "name": "UsersPageQuery",
    "operationKind": "query",
    "text": "query UsersPageQuery {\n  users {\n    id\n    name\n  }\n}\n"
  }
};
})();

(node as any).hash = "caa80c6bb7461144e712a2b59ee62835";

export default node;
