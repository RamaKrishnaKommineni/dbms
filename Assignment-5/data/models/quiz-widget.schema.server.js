const mongoose = require('mongoose');

const questionSchema = require('./question.schema.server')
const quizWidgetSchema = mongoose.Schema({
    questions: [{
        type: Number,
        ref: 'QuestionModel'
    }]
}, {collection: 'question-widgets'});

module.exports = quizWidgetSchema;