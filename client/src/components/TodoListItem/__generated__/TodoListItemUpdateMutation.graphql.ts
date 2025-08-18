/**
 * @generated SignedSource<<d540945d4fd90f6d2c7a7229d5c84320>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
export type UpdateTodoInput = {
  id: string;
  isCompleted?: boolean | null | undefined;
  title?: string | null | undefined;
};
export type TodoListItemUpdateMutation$variables = {
  input: UpdateTodoInput;
};
export type TodoListItemUpdateMutation$data = {
  readonly updateTodo: {
    readonly id: string;
  } | null | undefined;
};
export type TodoListItemUpdateMutation = {
  response: TodoListItemUpdateMutation$data;
  variables: TodoListItemUpdateMutation$variables;
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
    "name": "updateTodo",
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
    "name": "TodoListItemUpdateMutation",
    "selections": (v1/*: any*/),
    "type": "Mutation",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "TodoListItemUpdateMutation",
    "selections": (v1/*: any*/)
  },
  "params": {
    "cacheID": "1e023643403396f54f804b7736a56663",
    "id": null,
    "metadata": {},
    "name": "TodoListItemUpdateMutation",
    "operationKind": "mutation",
    "text": "mutation TodoListItemUpdateMutation(\n  $input: UpdateTodoInput!\n) {\n  updateTodo(input: $input) {\n    id\n  }\n}\n"
  }
};
})();

(node as any).hash = "4b41d56e234813fe39ce4f63f3f548cc";

export default node;
