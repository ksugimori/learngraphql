#!/bin/bash

# Git hooksディレクトリのパスを取得
HOOKS_DIR="$(git rev-parse --git-dir)/hooks"

# プロジェクトのhooksディレクトリから.git/hooksにコピー
cp hooks/* "$HOOKS_DIR/"

# 実行権限を付与
chmod +x "$HOOKS_DIR"/*

echo "Git hooks installed successfully!"