select distinct
    street.name,
    extract('month' from datetime)                                              as month,
    sum(case when crime.category='drugs' then 1 else 0 end  ) as drugs_crime_count,
    sum(case when stop_force.object_of_search='Controlled drugs' then 1 else 0 end  ) as drugs_stop_and_search_count,
    sum(case when crime.category='possession-of-weapons' then 1 else 0 end  ) as weapons_crime_count,
    sum(case when stop_force.object_of_search='Offensive weapons'  and stop_force.object_of_search='Firearms'  then 1 else 0 end  ) as weapons_stop_and_search_count,
    sum(case when crime.category='theft-from-the-person' and crime.category='shoplifting' then 1 else 0 end  ) as theft_crime_count,
    sum(case when stop_force.object_of_search='Stolen goods'   then 1 else 0 end  ) as theft_stop_and_search_count

                from location
         inner join stop_force on stop_force.location_id = location.id
         inner join street on location.street_id = street.id
         inner join crime  on location.id = crime.location_id

where datetime >= '2019-07-01'
  and datetime <= '2019-09-30' and  month >= '2019-07-01'
  and month <= '2019-09-30' and outcome_object_id='bu-arrest'
group by street.name, extract('month' from datetime)
;