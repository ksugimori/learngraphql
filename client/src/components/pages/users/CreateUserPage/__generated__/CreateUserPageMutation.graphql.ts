/**
 * @generated SignedSource<<75efd4bb3a4ea4ada1ef66c382afda0c>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
export type CreateUserInput = {
  name: string;
};
export type CreateUserPageMutation$variables = {
  input: CreateUserInput;
};
export type CreateUserPageMutation$data = {
  readonly createUser: {
    readonly id: string;
  };
};
export type CreateUserPageMutation = {
  response: CreateUserPageMutation$data;
  variables: CreateUserPageMutation$variables;
};

const node: ConcreteRequest = (function(){
var v0 = [
  {
    "defaultValue": null,
    "kind": "LocalArgument",
    "name": "input"
  }
],
v1 = [
  {
    "alias": null,
    "args": [
      {
        "kind": "Variable",
        "name": "input",
        "variableName": "input"
      }
    ],
    "concreteType": "User",
    "kind": "LinkedField",
    "name": "createUser",
    "plural": false,
    "selections": [
      {
        "alias": null,
        "args": null,
        "kind": "ScalarField",
        "name": "id",
        "storageKey": null
      }
    ],
    "storageKey": null
  }
];
return {
  "fragment": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Fragment",
    "metadata": null,
    "name": "CreateUserPageMutation",
    "selections": (v1/*: any*/),
    "type": "Mutation",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "CreateUserPageMutation",
    "selections": (v1/*: any*/)
  },
  "params": {
    "cacheID": "56a66d5fee79ff035385133b30da5370",
    "id": null,
    "metadata": {},
    "name": "CreateUserPageMutation",
    "operationKind": "mutation",
    "text": "mutation CreateUserPageMutation(\n  $input: CreateUserInput!\n) {\n  createUser(input: $input) {\n    id\n  }\n}\n"
  }
};
})();

(node as any).hash = "e0bc6af54a01177636435010fa185025";

export default node;
