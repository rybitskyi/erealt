﻿insert into stat_abstract_object
(id,
dtype,
  "version",
  creation_date,
  price,
  street_object_no,
  plottage,
  build_floor,
  flat_area,
  flat_floor,
  room_count,
  district,
  region,
  street)
select   
id,
dtype,
  "version",
  creation_date,
  price,
  street_object_no,
  plottage,
  build_floor,
  flat_area,
  flat_floor,
  room_count,
  district,
  region,
  street
 from abstract_object; 