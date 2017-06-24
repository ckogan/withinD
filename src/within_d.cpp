#include <Rcpp.h>
using namespace Rcpp;

// This is a simple example of exporting a C++ function to R. You can
// source this function into an R session using the Rcpp::sourceCpp
// function (or via the Source button on the editor toolbar). Learn
// more about Rcpp at:
//
//   http://www.rcpp.org/
//   http://adv-r.had.co.nz/Rcpp.html
//   http://gallery.rcpp.org/
//

// [[Rcpp::export]]
LogicalVector within_d(NumericVector id, NumericVector x, NumericVector y, double x_ref, double y_ref, double max_distance) {
  //Determine those points that are outside the outer square
  int n = id.size();
  const double d = max_distance;
  const double big_left = x_ref - d;
  const double big_right = x_ref + d;
  const double big_bottom = y_ref - d;
  const double big_top = y_ref +d;
  const double dsq = d / sqrt(2);
  const double small_left = x_ref - dsq;
  const double small_right = x_ref + dsq;
  const double small_bottom = y_ref - dsq;
  const double small_top = y_ref + dsq;
  LogicalVector inside_circle(n);
  for(int i=0; i<n; ++i){
  double yi = y[i];
  double xi = x[i];
  if(!(xi > big_right | xi < big_left | yi < big_bottom | yi > big_top)){
    // Point not outside big square
    if(!(xi < small_right & xi > small_left & yi > small_bottom & yi < small_top)) {
      double rx = xi - x_ref;
      double ry = yi - y_ref;
      if(rx*rx+ ry*ry < d*d) {
        inside_circle[i] = true;
      }
    } else {
      inside_circle[i] = true;
    }
  }
  }
  return inside_circle;
}


// You can include R code blocks in C++ files processed with sourceCpp
// (useful for testing and development). The R code will be automatically
// run after the compilation.
//

/*** R
library(dplyr)
library(ggplot2)
set.seed(101)
n <- 100
x <- rnorm(n)
y <- rnorm(n)
id <- 1:n
x_ref <- 0
y_ref <- 0
id_yes <- within_d(id, x, y, x_ref, y_ref, 1.2)
data <- data.frame(id = id, x = x, y = y) %>%
mutate(
within = id %in% id_yes,
within = factor(as.integer(within))
)
*/
