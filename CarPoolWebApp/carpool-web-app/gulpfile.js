var gulp = require('gulp');
var concat = require('gulp-concat');
var concatVendor = require('gulp-concat-vendor');
var uglify = require('gulp-uglify');
var minify = require('gulp-minify-css')
var gulpMainBowerFiles = require('gulp-main-bower-files');

var mainBowerFiles = require('main-bower-files');
var inject = require('gulp-inject');
var runSequence = require('gulp-run-sequence');
var gzip = require('gulp-gzip');
var clone = require('gulp-clone');
var series = require('stream-series');
var filter = require('gulp-filter');
var gulpFilter = filter;
var jshint = require('gulp-jshint');
//var Server = require('karma').Server;

var vendorJs;
var vendorCss;

gulp.task('minifiyVendorJSFiles', function() {
    var filterJS = filter('**/*.js');
    vendorJs = filter("**/*vendor.min.js");

    return gulp.src('./bower.json')
        .pipe(gulpMainBowerFiles({
            overrides: {
                bootstrap: {
                    main: [
                        './dist/js/bootstrap.js',
                        './dist/css/*.min.*',
                        './dist/fonts/*.*'
                    ]
                },
            }
        }))
        .pipe(filterJS)
        .pipe(concat('vendor.min.js'))
        .pipe(uglify())
        .pipe(vendorJs)
        .pipe(gulp.dest('./src/main/resources/static/js'));
});

gulp.task('minifyVendorCSSFiles', function() {
    var filterCSS = filter('**/*.css');
    vendorCss = filter('**/*vendor.min.css');

    return gulp.src('./bower.json')
        .pipe(gulpMainBowerFiles({
            overrides: {
                bootstrap: {
                    main: [
                        './dist/js/bootstrap.js',
                        './dist/css/*.min.*',
                        './dist/fonts/*.*'
                    ]
                },
                "font-awesome": {
                    "main": [
                        "css/*.min.*",
                        "fonts/*.*"
                    ]
                }
            }
        }))
        .pipe(filterCSS)
        .pipe(concat('vendor.min.css'))
        .pipe(minify())
        .pipe(vendorCss)
        .pipe(gulp.dest('./src/main/resources/static/css'));
});

gulp.task('copyFonts', function() {
    gulp.src([
            './bower_components/bootstrap/fonts/*.{ttf,woff,woff2,eof,svg,eot}',
            './bower_components/font-awesome/fonts/*.{ttf,woff,woff2,eof,svg,eot}'
        ])
        .pipe(gulp.dest('./src/main/resources/static/fonts/'));

});


gulp.task('lint', function() {
    return gulp.src('./src/main/resources/static/js/app/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

/*

gulp.task('test', function() {
	 new Server({
		    configFile: __dirname + '/karma.conf.js',
		    singleRun: true
		  }, function(){}).start();
});
*/

// Default Task
gulp.task('default', ['minifiyVendorJSFiles', 'minifyVendorCSSFiles', 'copyFonts', 'lint']);