/**
 * @generated SignedSource<<014e9c0da6ca1f6275415fdd072625f0>>
 * @lightSyntaxTransform
 * @nogrep
 */

/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from 'relay-runtime';
export type TodoListItemDeleteMutation$variables = {
  id: string;
};
export type TodoListItemDeleteMutation$data = {
  readonly deleteTodo: string | null | undefined;
};
export type TodoListItemDeleteMutation = {
  response: TodoListItemDeleteMutation$data;
  variables: TodoListItemDeleteMutation$variables;
};

const node: ConcreteRequest = (function(){
var v0 = [
  {
    "defaultValue": null,
    "kind": "LocalArgument",
    "name": "id"
  }
],
v1 = [
  {
    "alias": null,
    "args": [
      {
        "kind": "Variable",
        "name": "id",
        "variableName": "id"
      }
    ],
    "kind": "ScalarField",
    "name": "deleteTodo",
    "storageKey": null
  }
];
return {
  "fragment": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Fragment",
    "metadata": null,
    "name": "TodoListItemDeleteMutation",
    "selections": (v1/*: any*/),
    "type": "Mutation",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "TodoListItemDeleteMutation",
    "selections": (v1/*: any*/)
  },
  "params": {
    "cacheID": "ea9e4d501c909f2f96b62f8882facc34",
    "id": null,
    "metadata": {},
    "name": "TodoListItemDeleteMutation",
    "operationKind": "mutation",
    "text": "mutation TodoListItemDeleteMutation(\n  $id: ID!\n) {\n  deleteTodo(id: $id)\n}\n"
  }
};
})();

(node as any).hash = "360d807089e607eb234ed25246209abf";

export default node;
