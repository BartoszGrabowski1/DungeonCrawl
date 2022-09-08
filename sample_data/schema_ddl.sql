DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    player_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    dmg integer NOT NULL,
    ap integer NOT NULL,
    bp integer NOT NULL,
    experience integer NOT NULL,
    mana integer NOT NULL
);

DROP TABLE IF EXISTS public.items;
CREATE TABLE public.items(
    id serial NOT NULL PRIMARY KEY,
    x integer,
    y integer,
    item_name text NOT NULL,
    item_description text NOT NULL,
    item_value integer,
    is_item_wore boolean,
    player_id integer NOT NULL
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.items
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);