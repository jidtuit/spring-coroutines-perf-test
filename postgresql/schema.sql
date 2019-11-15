CREATE TABLE IF NOT EXISTS public.coroutines_save_and_calc
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    v0 character varying(20),
    v1 character varying(20),
    v2 character varying(20),
    PRIMARY KEY (id)
);

ALTER TABLE public.coroutines_save_and_calc
    OWNER to admin;