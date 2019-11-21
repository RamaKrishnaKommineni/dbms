require('./data/db')();

const universityDao = require('./data/daos/university.dao.server');
const assert = require('assert');

truncateDatabase = () => universityDao.truncateDatabase();

populateDatabase = () => universityDao.populateDatabase();

testStudentsInitialCount = () =>
    universityDao.findAllStudents()
        .then(students => assert(students.length == 2))
        .catch(err => console.log(err));

testQuestionsInitialCount = () =>
    universityDao.findAllQuestions()
        .then(questions => assert(questions.length == 4))
        .catch(err => console.log(err));

testAnswersInitialCount = () =>
    universityDao.findAllAnswers()
        .then(answers => assert(answers.length == 8))
        .catch(err => console.log(err));

testDeleteAnswer = () =>
    universityDao.deleteAnswer(890)
        .then(() => universityDao.findAllAnswers())
        .then(answers => assert(answers.length == 7))
        .then(() => universityDao.findAnswersByStudent(234))
        .then(answers => assert(answers.length == 3))
        .catch(err => console.log(err));

testDeleteQuestion = () =>
    universityDao.deleteQuestion(321)
        .then(() => universityDao.findAllQuestions())
        .then(questions => assert(questions.length == 3))
        .catch(err => console.log(err));

testDeleteStudent = () =>
    universityDao.deleteStudent(234)
        .then(() => universityDao.findAllStudents())
        .then(students => assert(students.length == 1))
        .catch(err => console.log(err));

truncateDatabase()
    .then(() => populateDatabase())
    .then(() => testStudentsInitialCount())
    .then(() => testQuestionsInitialCount())
    .then(() => testAnswersInitialCount())
    .then(() => testDeleteAnswer())
    .then(() => testDeleteQuestion())
    .then(() => testDeleteStudent())
    .then(() => require('mongoose').connection.close());