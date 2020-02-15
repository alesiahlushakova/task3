select distinct street_id,
                street.name,
                count(*) over (partition by street.name)                                        as crime_count,
                (count(*) over (partition by street.name)::numeric / count(*) over ()::numeric) as percentage

from location
         inner join crime on crime.location_id = location.id
         inner join street on location.street_id = street.id
         inner join outcome_status on crime.outcome_status_id = outcome_status.id
where crime.category = 'drugs'
  and month >= '2019-07-01'
  and month <= '2019-09-01'
  and outcome_status is not null
group by street_id, street.name
;