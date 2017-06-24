#' Determine which points are within a distance of a reference point
#'@examples
#' library(dplyr)
#' library(ggplot2)
#' n <- 100
#' x <- round(rnorm(n),3)
#' y <- round(rnorm(n),3)
#' id <- 1:n
#' x_ref <- 0
#' y_ref <- 0
#' id_yes <- rwithin_d(id, x, y, x_ref, y_ref, 1.2)
#' data <- data.frame(id = id, x = x, y = y) %>%
#' mutate(
#' within = id %in% id_yes,
#' within = factor(as.integer(within))
#' )
#'
#' jx <- paste("{", paste0(x, collapse = ","), "}")
#' jy <- paste("{", paste0(y, collapse = ","), "}")
#' jid <- paste("{", paste0(id, collapse = ","), "}")
#'
#' data %>% ggplot(aes(x, y, colour = within)) + geom_point()
rwithin_d <- function(id, x, y, x_ref, y_ref, max_distance) {
  # Determine those points that are outside the outer square
  d <- max_distance
  outside <- x > (x_ref+d) | x < (x_ref - d) | y > (y_ref + d) | y < (y_ref - d)
  dsq <- d/sqrt(2)
  inside <- x < (x_ref + dsq) & x > (x_ref - dsq) & y < (y_ref + dsq) & y > (y_ref - dsq)

  ix_border <- which(!outside & !inside)
  ix_yes <- (x[ix_border] - x_ref)^2 + (y[ix_border] - y_ref)^2 < d^2
  sort(union(id[inside], id[ix_border][ix_yes]))
}
