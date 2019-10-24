Delimiter $$
create procedure endorsedUsersForWeek(in start date, in end date)
begin
	select person, approved 
	from (select 
			user.person, user.user_agreement as approved, 
			sum(case when correctAnswer then 1 end) as numOfCorrectAnswers
			where
				user.person = question.askedBy
				and answer.postedBy = user.person
				and answer.postedOn between start and end
			group by user.person
			order by numOfCorrectAnswers desc limit 5
			) as sowmika 
	order by person;
	end$$
	DELIMITER ;
    
    drop procedure endorsedUsersForWeek;