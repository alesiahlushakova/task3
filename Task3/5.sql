select distinct
    street.name,
                mode() within group (order by stop_force.age_range ) as age_range,
                mode() within group ( order by stop_force.gender ) as gender,
    mode() within group (order by stop_force.officer_defined_ethnicity ) as ethnicity,
    mode() within group ( order by stop_force.object_of_search ) as object_of_search,
    mode() within group (order by stop_force.outcome_object_id ) as outcome

from location
         inner join stop_force on stop_force.location_id = location.id
         inner join street on location.street_id = street.id

where datetime >= '2019-07-01'
  and datetime <= '2019-09-30'
group by street.name
;