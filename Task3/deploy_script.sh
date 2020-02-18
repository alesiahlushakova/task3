#! bin/bash
database="task2"
status=$(service postgresql-10 status | grep "running")
QUIET_VERBOSE="-q"
QUERY="1.sql"

info()
{
        clear screen
        echo "Information about this script:"
        echo "-------------------------------------------------------------------------"
        echo "Script creates database and tables, runs Java app and loads data from API "
        echo "-d drops db"
        echo "-h or --help for help"
        echo "-m calls maven clean package phases"
        echo "-v disables silent start"
        echo "-p print (n-2) rows of resultset"
        echo "-n to name output file"
        echo "-1 for most dangerous strees query"
        echo "-2 for motth to month crime volume comparison"
        echo "-3 for crimes with specified outcome status"
        echo "-4 for stop and seacrh staticstics by ethnicity"
        echo "-5 for most probable snapshor of crimes"
        echo "-6 for stop and search correlation"
        exit 0
}
start_deploy()
{
if [ -z "$status"  ]
then
        service postgresql-10 restart
        check_status
else
        echo "service postgresql  is running"
        check_db
fi
}


check_db()
{
if [ "$( psql -U postgres -w -tAc "SELECT 1 FROM pg_database WHERE datname='$database'" )" = '1' ]
then
    echo "Database already exists"
        check_tables_exist
else
   echo "CREATE DATABASE task2  WITH OWNER = postgres ENCODING = 'UTF8';" | psql -U postgres -w

fi
}

create_tables()
{
psql -U postgres -w -q -d $database -f "dbCreation.sql"

}

dropDb()
{
echo "DROP DATABASE task2;" | psql -U postgres -w
}

check_tables_exist()
{
crime_table=$(psql -U postgres $database -c \\dt | grep "crime")
location_table=$(psql -U postgres $database -c \\dt | grep "location")
street_table=$(psql -U postgres $database -c \\dt | grep "street")
status_table=$(psql -U postgres $database -c \\dt | grep "outcome_status")
if [ -z "$crime_table" ] || [ -z "location_table" ] || [ -z "$street_table" ] || [ -z "$status_table" ]
then
        create_tables
        check_tables_exist
else
        echo "tables alredy exist"

java -Djava.util.concurrent.ForkJoinPool.common.parallelism=8 -jar /root/3task/task3/Task3/bigdata_task2/target/bigdata_task2-1.0-SNAPSHOT.jar -path=/root/3task/task3/Task3/bigdata_task2/data/LondonStations.csv -date=2019-07 -dateEnd=2019-09 --chosen_api=crime_level


fi
}

mvn_clean_package()
{
    mvn "$QUIET_VERBOSE" -f "/root/3task/task3/Task3/bigdata_task2" clean package
}

exec_query()
{

psql -U postgres -w -q -d $database -f "$QUERY" > "$name"
sed -n 1,"$rows"p "1.txt"
}

while getopts "dn:hp:rvm123456" opt
do
case $opt in
d) dropDb;;
h) info;;
m) mvn_clean_package;;
n) name=${OPTARG};;
v) QUIET_VERBOSE="-v";;
1) QUERY="1.sql" ;;
2) QUERY="2.sql";;
3) QUERY="3.sql";;
4) QUERY="4.sql";;
5) QUERY="5.sql";;
6) QUERY="6.sql";;
p) rows=${OPTARG};;
r) start_deploy;;
esac
done
exec_query
