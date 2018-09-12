function formatLongSentence(data,maxSize) {
    if (data.length >= maxSize) {
        return data.substring(0, maxSize) + '...';
    }
    return data;
}

function validateInput(data) {
    if ($.trim(data) == null || $.trim(data) == '') {
        return false;
    }
    return true;
}

function pointEmptyInput(em) {
    if(validateInput(em.val())){
        return true;
    }
    em.css({
        'border-color': 'red'
    });
    return false;
}

function bindBlurAndFoucs(em) {
    em.bind('blur', function () {
        pointEmptyInput(em);
    });
    em.bind('focus', function () {
        em.css({
            'border-color': '#ccc'
        });
    });
}