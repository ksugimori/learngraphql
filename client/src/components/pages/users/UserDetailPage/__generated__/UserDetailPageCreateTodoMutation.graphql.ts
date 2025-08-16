/**
 * @generated SignedSource<<6f8dffeefefbcc3b61a46c85e704e74f>>
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
export type UserDetailPageCreateTodoMutation$variables = {
  input: CreateTodoInput;
};
export type UserDetailPageCreateTodoMutation$data = {
  readonly createTodo: {
    readonly id: string;
  } | null | undefined;
};
export type UserDetailPageCreateTodoMutation = {
  response: UserDetailPageCreateTodoMutation$data;
  variables: UserDetailPageCreateTodoMutation$variables;
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
    "name": "UserDetailPageCreateTodoMutation",
    "selections": (v1/*: any*/),
    "type": "Mutation",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "UserDetailPageCreateTodoMutation",
    "selections": (v1/*: any*/)
  },
  "params": {
    "cacheID": "3a012aad8c43f2d2b4d82144f574469d",
    "id": null,
    "metadata": {},
    "name": "UserDetailPageCreateTodoMutation",
    "operationKind": "mutation",
    "text": "mutation UserDetailPageCreateTodoMutation(\n  $input: CreateTodoInput!\n) {\n  createTodo(input: $input) {\n    id\n  }\n}\n"
  }
};
})();

(node as any).hash = "c95bd7295c4904356b9cddc48dc904b9";

export default node;
