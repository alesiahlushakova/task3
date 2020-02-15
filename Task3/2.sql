select category,
       month,
       lag(count(*)) over (partition by category)                              as prev_month_count,
       count(*)                                                                as current_month,
       count(*) - lag(count(*)) over (partition by category)                   as delta,
       round((((count(*) - lag(count(*)) over (partition by category)) /
               lag(count(*)) over (partition by category)::numeric) * 100), 2) as growth_rate
from crime
where month >= '2019-07-01'
  and month <= '2019-09-01'
group by category, month;