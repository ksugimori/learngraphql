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

INSERT INTO todos (user_id, title, is_completed) VALUES(1, '買い物に行く', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(1, '洗濯をする', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(1, '部屋の掃除', true);

INSERT INTO todos (user_id, title, is_completed) VALUES(2, '病院に行く', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '薬を飲む', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '運動する', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '本を読む', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(2, '友達に連絡する', false);

INSERT INTO todos (user_id, title, is_completed) VALUES(3, '会議の準備', true);

INSERT INTO todos (user_id, title, is_completed) VALUES(4, '料理を作る', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(4, '映画を見る', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(4, '散歩する', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(4, '家計簿をつける', true);

INSERT INTO todos (user_id, title, is_completed) VALUES(6, '銀行に行く', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(6, 'メールを返信する', true);

INSERT INTO todos (user_id, title, is_completed) VALUES(7, '歯医者の予約', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(7, '車の点検', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(7, '税金の手続き', false);

INSERT INTO todos (user_id, title, is_completed) VALUES(8, '旅行の計画', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, 'プレゼントを買う', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '写真を整理する', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(8, '英語の勉強', false);

INSERT INTO todos (user_id, title, is_completed) VALUES(9, '資料作成', true);

INSERT INTO todos (user_id, title, is_completed) VALUES(10, '美容院の予約', false);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, 'ヨガのレッスン', true);
INSERT INTO todos (user_id, title, is_completed) VALUES(10, '家族との食事', false);