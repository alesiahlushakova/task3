select distinct street.id,
                name,
                'from 2019-05-30 till 2020-01-01' as date,
                count(*) over
                    (partition by name )          as count
from location
         inner join crime on crime.location_id = location.id
         inner join street on location.street_id = street.id
where month > '2019-05-30'
  and month < '2020-01-01'
order by count desc
limit 10;
