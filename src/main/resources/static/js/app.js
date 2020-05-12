(function () {

    var calendarFahrenheitEls = document.querySelectorAll('#InSight-Weather-Report .lbl_fahrenheit');
    var calendarCelsiusEls = document.querySelectorAll('#InSight-Weather-Report .lbl_celsius');
    var tableFahrenheitEl = document.querySelector('#weather_observation .lbl_fahrenheit');
    var tableCelsiusEl = document.querySelector('#weather_observation .lbl_celsius');
    var tableMilesPerHourEl = document.querySelector('#weather_observation .lbl_mph');
    var tableMetersPerSecondEl = document.querySelector('#weather_observation .lbl_mps');

    document.addEventListener('click', function(e) {
        if (e.target) {
            if (e.target.className.indexOf('lbl_fahrenheit') >= 0) {
                document.body.setAttribute('data-temperature-mode', 'fahrenheit');
                toggleFahrenheitOn();
            } else if (e.target.className.indexOf('lbl_celsius') >= 0) {
                document.body.setAttribute('data-temperature-mode', 'celsius');
                toggleCelsiusOn();
            } else if (e.target.className.indexOf('lbl_mph') >= 0) {
                document.body.setAttribute('data-speed-mode', 'miles-per-hour');
                toggleMilesPerHourOn();
            } else if (e.target.className.indexOf('lbl_mps') >= 0) {
                document.body.setAttribute('data-speed-mode', 'meters-per-second');
                toggleMetersPerSecondOn();
            }
        }
    });

    var toggleFahrenheitOn = function() {
        for (var i = 0; i < calendarCelsiusEls.length; i++) {
            calendarCelsiusEls[i].classList.add('fadeWhite');
        }
        tableCelsiusEl.classList.add('fadeBlack');
        for (var n = 0; n < calendarFahrenheitEls.length; n++) {
            calendarFahrenheitEls[n].classList.remove('fadeWhite');
        }
        tableFahrenheitEl.classList.remove('fadeBlack');
    };

    var toggleCelsiusOn = function() {
        for (var i = 0; i < calendarFahrenheitEls.length; i++) {
            calendarFahrenheitEls[i].classList.add('fadeWhite');
        }
        tableFahrenheitEl.classList.add('fadeBlack');
        for (var n = 0; n < calendarCelsiusEls.length; n++) {
            calendarCelsiusEls[n].classList.remove('fadeWhite');
        }
        tableCelsiusEl.classList.remove('fadeBlack');
    };

    var toggleMilesPerHourOn = function() {
        tableMetersPerSecondEl.classList.add('fadeBlack');
        tableMilesPerHourEl.classList.remove('fadeBlack');
    };

    var toggleMetersPerSecondOn = function() {
        tableMilesPerHourEl.classList.add('fadeBlack');
        tableMetersPerSecondEl.classList.remove('fadeBlack');
    };

})();