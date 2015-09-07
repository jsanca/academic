
QUnit.module('$ like library challenge!');

QUnit.test( 'DOM manipulation', function(assert) {

    console.log("Running DOM manipulation");
    var $container = $('#container');
    var container = document.getElementById('container');
    
    assert.ok(!!$container, '$("#container") does not return undefined');
    assert.equal($container.el.id, container.id, '$("#container") return the correct element');

    var $p = $('<p></p>');

    assert.ok(!!$p, '$("<p></p>") does not return undefined');
    assert.equal($p, '<p></p>', '$("<p></p>") creates the correct element');

    $p.html('<strong>Some html content</strong>');

    $('#container').html($p);

    assert.equal(container.innerHTML, '<p><strong>Some html content</strong></p>', '$().html() works as expected');
    assert.equal($container.text(), container.textContent, '$().text() works as expected');

});

QUnit.test('Utilities (Functional programming)', function(assert) {
    var array = [1, 2, 3, 4, 5];

    assert.ok(true, '$.each()');
    assert.ok(true, '$.inArray()');

    assert.ok(true, '$.isArray()');
    assert.ok(true, '$.isFunction()');
    assert.ok(true, '$.isNumeric()');
});

QUnit.test('Events', function(assert) {
    assert.ok(true, '$().on()');
});