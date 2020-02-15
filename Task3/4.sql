with full_select as (select *
                     from stop_force

                     where datetime >= '2019-07-01'
                       and datetime <= '2019-09-30')
select distinct full_select.officer_defined_ethnicity,
                extract('month' from datetime)                                                                    as month,

                 count(*)
                       as occurency,
                sum(case when outcome_object_id='bu-arrest' then 1 else 0 end)::numeric /
                 ( count(full_select.officer_defined_ethnicity)) *100
                 as arrest_rate,
                sum(case when outcome_object_id='bu-no-further-action' then 1 else 0 end)::numeric /
                ( count(full_select.officer_defined_ethnicity)) *
                100 as no_further_action_rate,
                sum(case when outcome_object_id!='bu-no-further-action' and outcome_object_id!='bu-arrest' then 1 else 0 end)::numeric /
                ( count(full_select.officer_defined_ethnicity)) *
                100 as other_rate,
                   mode() within group  ( order by full_select.object_of_search ) as most_popular_object_of_search


from full_select
where datetime >= '2019-07-01'
  and datetime <= '2019-09-30' and full_select.officer_defined_ethnicity is not null
group by extract('month' from datetime), full_select.officer_defined_ethnicity
order by month;