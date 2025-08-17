/**
 * @generated SignedSource<<b6554c11fd3cc253894521d7a80d17b9>>
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
export type CreateTodoFormMutation$variables = {
  input: CreateTodoInput;
};
export type CreateTodoFormMutation$data = {
  readonly createTodo: {
    readonly id: string;
  } | null | undefined;
};
export type CreateTodoFormMutation = {
  response: CreateTodoFormMutation$data;
  variables: CreateTodoFormMutation$variables;
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
    "name": "CreateTodoFormMutation",
    "selections": (v1/*: any*/),
    "type": "Mutation",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "CreateTodoFormMutation",
    "selections": (v1/*: any*/)
  },
  "params": {
    "cacheID": "e1f5f66f546fc3d9238f38c125e30b4f",
    "id": null,
    "metadata": {},
    "name": "CreateTodoFormMutation",
    "operationKind": "mutation",
    "text": "mutation CreateTodoFormMutation(\n  $input: CreateTodoInput!\n) {\n  createTodo(input: $input) {\n    id\n  }\n}\n"
  }
};
})();

(node as any).hash = "77ab62fae4f929acf4cf665fa3f101fc";

export default node;
