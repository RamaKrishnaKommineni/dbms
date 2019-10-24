Delimiter $$
create procedure getUnansweredQuestions()
begin
	select 
		pdf.qid as id,
		pdf.qtext as text,
		pdf.module as module,
		MAX(pdf.an) as answeredNumber
	from
		(select 
			question.text as qtext,
			question.id as qid,
			count(answer.id) as an,
			sum(case when correctAnswer then 1 end) as ca,
			question.module
		where
			user.person = question.askedBy
			and answer.postedBy = user.person
		group by question.id
		having ca = 0 or MAX(answer.correctAnswer) is null) as pdf
	group by module;
end$$
DELIMITER ;

drop procedure getUnansweredQuestions;