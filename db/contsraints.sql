
-- setup contraints on t_user_ideal_ethnicity
alter table t_user_ideal_ethnicity add CONSTRAINT fk_user_ideal_ethnicity_user FOREIGN KEY (user_id) REFERENCES t_user (user_id);
alter table t_user_ideal_ethnicity add CONSTRAINT fk_user_ideal_ethnicity_ethnicity FOREIGN KEY (ideal_ethnicity_id) REFERENCES t_ethnicity (ethnicity_id);

-- setup constraints on t_user_ideal_lookingfor
alter table t_user_ideal_lookingfor add CONSTRAINT fk_user_ideal_lookingfor_user FOREIGN KEY (user_id) REFERENCES t_user (user_id);
alter table t_user_ideal_lookingfor add CONSTRAINT fk_user_ideal_lookingfor_lookingfor FOREIGN KEY (ideal_lookingfor_id) REFERENCES t_lookingfor (lookingfor_id);

--setup constraints on t_ideal_bodytype
alter table t_ideal_bodytype add CONSTRAINT fk_user_ideal_bodytype_user FOREIGN KEY (user_id) REFERENCES t_user (user_id);
alter table t_ideal_bodytype add CONSTRAINT fk_user_ideal_bodytype_bodytype FOREIGN KEY (ideal_bodytype_id) REFERENCES t_bodytype (bodytype_id);


