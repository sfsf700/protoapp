CREATE TABLE IF NOT EXISTS stocks (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  category VARCHAR(50) NOT NULL,
  title VARCHAR(50) NOT NULL,
  quantity INT NOT NULL,
  ymd date NOT NULL,
  update_datetime timestamp
);

CREATE TABLE IF NOT EXISTS category_code(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  group_cd varchar(2),
  cd varchar(2),
  name varchar(20)
);

DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS login_user;
DROP TABLE IF EXISTS roles;

-- ロール
CREATE TABLE roles(
    id INTEGER PRIMARY KEY,    -- ロールのID
    name VARCHAR(32) NOT NULL  -- ロールの名前
);

-- ユーザー
CREATE TABLE login_user(
    id INTEGER PRIMARY KEY,         -- ユーザーのID
    name VARCHAR(128) NOT NULL,     -- ユーザーの表示名
    email VARCHAR(256) NOT NULL,    -- メールアドレス（ログイン時に利用）
    password VARCHAR(128) NOT NULL  -- ハッシュ化済みのパスワード
);

-- ユーザーとロールの対応付け
CREATE TABLE user_role(
    user_id INTEGER,    -- ユーザーのID
    role_id INTEGER,    -- ロールのID
    CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES login_user(id),
    CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);