delete from abstract_object where creation_date <= (now() - interval '3 months');
