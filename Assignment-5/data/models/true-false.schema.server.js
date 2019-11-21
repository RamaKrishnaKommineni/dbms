const mongoose = require('mongoose');

const trueFalseSchema = mongoose.Schema({
    _id: Number,
    isTrue: Boolean
});

module.exports = trueFalseSchema;