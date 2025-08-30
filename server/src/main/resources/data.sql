INSERT INTO users (name) VALUES ('田中太郎');
INSERT INTO users (name) VALUES ('佐藤花子');
INSERT INTO users (name) VALUES ('鈴木一郎');
INSERT INTO users (name) VALUES ('高橋美咲');
INSERT INTO users (name) VALUES ('伊藤健太');
INSERT INTO users (name) VALUES ('渡辺由美');
INSERT INTO users (name) VALUES ('山田隆');
INSERT INTO users (name) VALUES ('中村あや');
INSERT INTO users (name) VALUES ('小林誠');
INSERT INTO users (name) VALUES ('加藤麻衣');
INSERT INTO users (name) VALUES ('松本大輔');
INSERT INTO users (name) VALUES ('斎藤理恵');
INSERT INTO users (name) VALUES ('森本翔');
INSERT INTO users (name) VALUES ('石井美和');
INSERT INTO users (name) VALUES ('岡田直樹');

-- 田中太郎 (5件)
INSERT INTO todos (user_id, title, is_completed) VALUES(1, 'スーパーで夕食の材料を買う', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(1, '洗濯物を干す', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(1, '会社の資料を作成', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(1, '子供を保育園に迎えに行く', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(1, '週末の旅行の予約', false);

-- 佐藤花子 (10件)
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '歯医者の予約を取る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '友人の誕生日プレゼントを選ぶ', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '銀行で振込手続き', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '部屋の模様替え', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '英会話レッスンの復習', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, 'ペットの餌を買う', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '美容院に行く', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, 'ネットショッピングの注文確認', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '家計簿をつける', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, 'ランチの約束', false);

-- 鈴木一郎 (0件)

-- 高橋美咲 (3件)
INSERT INTO todos (user_id, title, is_completed) VALUES(4, '図書館で本を返却', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(4, '新しいレシピに挑戦', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(4, 'ジョギングコースを調べる', false);

-- 伊藤健太 (7件)
INSERT INTO todos (user_id, title, is_completed) VALUES(5, 'パスポートの更新', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(5, 'オンライン会議の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(5, '家族写真の整理', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(5, '自転車のメンテナンス', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(5, '夕食の献立を考える', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(5, '映画館のチケット予約', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(5, '観葉植物に水やり', false);

-- 渡辺由美 (12件)
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '仕事のメール返信', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, 'クリーニングに服を出す', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '夕食の買い出し', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '子供の宿題を見る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '週末のピクニック計画', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, 'スマホのバックアップ', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '新しい靴を買う', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '郵便物の受け取り', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, 'オンライン講座の受講', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '冷蔵庫の掃除', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '友人とカフェで会う', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, '健康診断の予約', false);

-- 山田隆 (2件)
INSERT INTO todos (user_id, title, is_completed) VALUES(7, '車のオイル交換', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(7, 'ゴミ出し', false);

-- 中村あや (25件)
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '朝食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '洗濯機を回す', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '仕事の資料作成', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '子供の送り迎え', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '夕食の買い物', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'ペットの散歩', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '読書タイム', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'ヨガのレッスン', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'ネットバンキングの確認', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '家族会議', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '新しいレシピを試す', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '観葉植物の手入れ', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'オンラインショッピング', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '写真の整理', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '週末の予定を立てる', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '掃除機をかける', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'お弁当作り', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '洗車', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '健康管理アプリの入力', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '家計簿の記入', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '友人にメール', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '映画鑑賞', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '新しい靴を買う', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '郵便物の確認', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'カフェで読書', false);

-- 小林誠 (1件)
INSERT INTO todos (user_id, title, is_completed) VALUES(9, 'パソコンのアップデート', false);

-- 加藤麻衣 (8件)
INSERT INTO todos (user_id, title, is_completed) VALUES(10, 'ランニング', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '朝食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '仕事のメール確認', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, 'スーパーで買い物', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '洗濯物をたたむ', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '読書', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '友人とランチ', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '映画館に行く', false);

-- 松本大輔 (20件)
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '朝の散歩', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '新聞を読む', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'コーヒーを淹れる', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'メールチェック', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '会議の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '昼食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '資料作成', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '電話対応', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '夕食の買い物', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '洗濯', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '掃除', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'ゴミ出し', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '読書', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'テレビを見る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '家計簿をつける', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'ネットショッピング', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'ペットの世話', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '運動', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, 'SNSのチェック', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(11, '日記を書く', false);

-- 斎藤理恵 (4件)
INSERT INTO todos (user_id, title, is_completed) VALUES(12, '朝食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(12, '洗濯', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(12, '仕事の資料作成', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(12, '夕食の買い物', false);

-- 森本翔 (15件)
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'ジョギング', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '朝食を作る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'メールチェック', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '会議の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '資料作成', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '昼食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '掃除', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'ゴミ出し', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '読書', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'テレビを見る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '家計簿をつける', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'ネットショッピング', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'ペットの世話', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, '運動', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(13, 'SNSのチェック', false);

-- 石井美和 (6件)
INSERT INTO todos (user_id, title, is_completed) VALUES(14, '朝食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(14, '洗濯', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(14, '仕事の資料作成', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(14, '夕食の買い物', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(14, '読書', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(14, '映画鑑賞', true);

-- 岡田直樹 (18件)
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '朝の散歩', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '新聞を読む', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, 'コーヒーを淹れる', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, 'メールチェック', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '会議の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '昼食の準備', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '資料作成', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '電話対応', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '夕食の買い物', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '洗濯', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '掃除', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, 'ゴミ出し', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '読書', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, 'テレビを見る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '家計簿をつける', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, 'ネットショッピング', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, 'ペットの世話', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(15, '運動', false);