/**
 * @generated SignedSource<<50b59a10c238d5942715b50a88240064>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
export type CreateTodoInput = {
  isCompleted?: boolean | null | undefined;
  title: string;
  userId: string;
};
export type CreateUserFormMutation$variables = {
  input: CreateTodoInput;
};
export type CreateUserFormMutation$data = {
  readonly createTodo: {
    readonly id: string;
  } | null | undefined;
};
export type CreateUserFormMutation = {
  response: CreateUserFormMutation$data;
  variables: CreateUserFormMutation$variables;
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
    "concreteType": "Todo",
    "kind": "LinkedField",
    "name": "createTodo",
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
    "name": "CreateUserFormMutation",
    "selections": (v1/*: any*/),
    "type": "Mutation",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "CreateUserFormMutation",
    "selections": (v1/*: any*/)
  },
  "params": {
    "cacheID": "3d145f57febd26a6b453aed0e22e2e1f",
    "id": null,
    "metadata": {},
    "name": "CreateUserFormMutation",
    "operationKind": "mutation",
    "text": "mutation CreateUserFormMutation(\n  $input: CreateTodoInput!\n) {\n  createTodo(input: $input) {\n    id\n  }\n}\n"
  }
};
})();

(node as any).hash = "4c68689cc475b31e66d44bd89b32a3bd";

export default node;
